/*
    ProjectEuler Implementations in C
    (c) Daniel Park 2013

    ///////////////////////
    Problem 11

    In the 20×20 grid below, four numbers along a diagonal line have been marked in red.

            08 02 22 97 38 15 00 40 00 75 04 05 07 78 52 12 50 77 91 08
            49 49 99 40 17 81 18 57 60 87 17 40 98 43 69 48 04 56 62 00
            81 49 31 73 55 79 14 29 93 71 40 67 53 88 30 03 49 13 36 65
            52 70 95 23 04 60 11 42 69 24 68 56 01 32 56 71 37 02 36 91
            22 31 16 71 51 67 63 89 41 92 36 54 22 40 40 28 66 33 13 80
            24 47 32 60 99 03 45 02 44 75 33 53 78 36 84 20 35 17 12 50
            32 98 81 28 64 23 67 10 26 38 40 67 59 54 70 66 18 38 64 70
            67 26 20 68 02 62 12 20 95 63 94 39 63 08 40 91 66 49 94 21
            24 55 58 05 66 73 99 26 97 17 78 78 96 83 14 88 34 89 63 72
            21 36 23 09 75 00 76 44 20 45 35 14 00 61 33 97 34 31 33 95
            78 17 53 28 22 75 31 67 15 94 03 80 04 62 16 14 09 53 56 92
            16 39 05 42 96 35 31 47 55 58 88 24 00 17 54 24 36 29 85 57
            86 56 00 48 35 71 89 07 05 44 44 37 44 60 21 58 51 54 17 58
            19 80 81 68 05 94 47 69 28 73 92 13 86 52 17 77 04 89 55 40
            04 52 08 83 97 35 99 16 07 97 57 32 16 26 26 79 33 27 98 66
            88 36 68 87 57 62 20 72 03 46 33 67 46 55 12 32 63 93 53 69
            04 42 16 73 38 25 39 11 24 94 72 18 08 46 29 32 40 62 76 36
            20 69 36 41 72 30 23 88 34 62 99 69 82 67 59 85 74 04 36 16
            20 73 35 29 78 31 90 01 74 31 49 71 48 86 81 16 23 57 05 54
            01 70 54 71 83 51 54 69 16 92 33 48 61 43 52 01 89 19 67 48

    The product of these numbers is 26 × 63 × 78 × 14 = 1788696.

    What is the greatest product of four adjacent numbers in the same direction (up, down, left, right, or diagonally) in the 20×20 grid?
    ///////////////////////

    Exit Errors:
    -3      -       Dimensions do not fit the provided data.
    -4      -       Thread Joining error


 */

//System Libraries
#include <iostream>
#include <iomanip>
#include <fstream>  //For reading the file
#include <string>
#include <sstream>
#include <cstdlib>

//Computation Types
enum computationTypes {VERTICAL, HORIZONTAL, FORWARD_DIAGONAL, BACKWARDS_DIAGONAL};

//Threading
#include <pthread.h>
static const unsigned int NUM_THREADS = 4;

int **numbers;
std::ifstream inFile;

struct multiplicationThreads
{
    int thread_id;
    int processResult;
    computationTypes processType;
    int *dimensions;
    int **numbers;

};

//It has been defined in the problem that the input array is 20x20. THis can be assumed constant throughout the lifespan of the program
const unsigned int GRID_SIZE = 20;
const unsigned int MULTIPLICATION_LENGTH = 4;   //Number of consecutive numbers to multiply

//Test-toggles
const bool TEST_ENABLE_ALL = false;
const bool TEST_READ_FILE = false && TEST_ENABLE_ALL;
const bool TEST_GET_DIMS = true && TEST_ENABLE_ALL;
const bool TEST_READ_DATA_IN = true && TEST_ENABLE_ALL;
const bool TEST_DISPLAY_THREAD_OUTPUTS = true && TEST_ENABLE_ALL;

//Indexing Constants
const unsigned int WIDTH = 0;
const unsigned int HEIGHT = 1;
//Prototype Methods
int *getDim(std::ifstream *file);
void *ComputeProducts(void *threadArg);  //Multithread calculator

//Program Entry
int main(int argc, char const *argv[])
{
    //The code should be able to read the array of characters from a file, this file should be passes as a raw text file to the final compiled program.

    //Before starting, check to see if there exists a valid argument count
    if (argc != 2)
    {
        std::cout << "Please provide a file to run against" << std::endl;
    }
    else
    {
        //Open the file

        std::cout << "Reading File (" << argv[1] << ")..." << std::endl;
        inFile.open(argv[1]);

        //make sure our file successfully opened
        if (!inFile.is_open())
        {
            std::cerr << "The input file failed to open" << std::endl;
        }

        //Run a test to print the file output. This code can be adapted for the reading method.
        if (TEST_READ_FILE)
        {
            std::string line;
            int lineNum = 1;
            while (std::getline(inFile, line))
            {
                std::cout << std::endl << "Retireving line " << lineNum++ << std::endl;
                std::istringstream stream(line);
                int x;
                while (stream.good())
                {
                    stream >> x;
                    //std::cout << std::setw(3) << x;
                }

            }

            std::cout << std::endl;
        }
    }

    //Check the dimensions of the input file. The input must be square
    int *dimensions = getDim(&inFile);
    if (dimensions == NULL)
    {
        std::cerr << "Input file is not sqaure." << std::endl;
        std::exit(-1);   //Exit due to invalid argument
    }
    else
    {
        if (TEST_GET_DIMS)
        {


            std::cout << "Width: " << dimensions[WIDTH] << std::endl << "Height: " << dimensions[HEIGHT] << std::endl;
        }

    }



    //Create a new 2D array to place the numbers read

    std::cout << "Creating array." << std::endl;
    numbers = new int*[dimensions[WIDTH]];
    for (int i = 0; i < GRID_SIZE; i++)
    {
        numbers[i] = new int[dimensions[HEIGHT]];
    }

    //Fill the array with read files;
    std::string line;
    int i_fill = 0;
    int j_fill = 0;

    while (std::getline(inFile, line))
    {
        std::cout << line << std::endl;
        std::istringstream stream(line);
        while (stream.good())
        {
            stream >> numbers[i_fill][j_fill];
            if (TEST_READ_DATA_IN)
            {
                std::cout << "Reading Position (" << j_fill << ", " << i_fill << ") with " << numbers[i_fill][j_fill] << std::endl;
            }
            i_fill++;
        }
        i_fill = 0;
        j_fill++;

    }

    //Close the file once all reading is complete
    inFile.close();

    //At this stage it would probably be a good idea to check if the size of multiplication requested is allowable given the input dimensions
    if (MULTIPLICATION_LENGTH > std::min(dimensions[WIDTH], dimensions[HEIGHT]))
    {
        std::cerr << "The multiplication size will not fit within the provided data.";
        exit(-3);
    }

    //Compute the maximum products in four different directions;
    //  -Vertically
    //  -Horizontally
    //  -Right Diagonal
    //  -Left Diagonal
    //
    //  Using a multi-threaded approach, we can condense the processing time, however, IOPS may be increased.

    //Create locations for the thread information
    struct multiplicationThreads mtData[NUM_THREADS];

    //Initialise the thread data
    for (int i = 0; i < NUM_THREADS; i++)
    {
        mtData[i].processResult = 0;
        mtData[i].processType = (computationTypes)i;
        mtData[i].thread_id = i;
        mtData[i].dimensions = dimensions;
        mtData[i].numbers = numbers;
    }

    //Create an addressable space for thread locations
    pthread_t threads[NUM_THREADS];
    std::cout << "Computing Data" << std::endl;
    for (int i = 0; i < 4; i++)
    {
        std::cout << "Creating Thread " << i << std::endl;;
        pthread_create(&threads[i], NULL, ComputeProducts, (void *)&mtData[i]);
    }

    //Wait for executing threads to finish before consolidating
    void *status;
    int rc;
    for (int i = 0; i < NUM_THREADS; i++)
    {
        rc = pthread_join(threads[i], &status);
        if (rc)
        {
            std::cerr << "Error joining thread." << rc << std::endl;
            exit(-4);
        }

        std::cout << "Thread " << mtData[i].thread_id << " Completed" << std::endl;
    }
    std::cerr << "Threads completed Running" << std::endl;

    //Consolidate and reduce the thread outputs
    if (TEST_DISPLAY_THREAD_OUTPUTS)
    {
        for (int i = 0; i < NUM_THREADS; i++)
        {
            std::cout << "Thread " << mtData[i].thread_id << " Produced " << mtData[i].processResult << std::endl;
        }

    }

    return 0;
}

/*
    Create a method which retrieves the square dimensions of an array. Returns null if the array is not square
 */
int *getDim(std::ifstream *file)
{

    int width = 0;
    int height = 0;

    std::string line;

    //Check if the file is open and readable
    if (!file->is_open())
    {
        std::cerr << "An internal IO error has occoured.";
        exit(-2);
    }

    while (std::getline(inFile, line))
    {
        height++;
        std::istringstream stream(line);
        int x;
        int tempWidth = 0;

        while (stream.good())
        {
            tempWidth++;
            stream >> x;
        }

        //If first line, set the width. Otherwise, check to see if with is constant for all lines
        if (height == 1)
        {
            width = tempWidth;
        }
        else
        {
            if (tempWidth != width)
            {
                return NULL;
            }
        }

    }

    //Reset reading status
    file->clear();
    file->seekg(0, std::ios::beg);

    //construct return object
    int *returnArr = new int[2];
    returnArr[WIDTH] = width;
    returnArr[HEIGHT] = height;

    return returnArr;

}

void *ComputeProducts(void *threadArg)
{
    //We must specify the field in which the calculations will start
    int ends[4];        //{i_starrt, i_end, j_start, j_end}
    int tempProd;


    //Retrieve the thread data
    struct multiplicationThreads *myData;
    myData = (struct multiplicationThreads *) threadArg;

    switch (myData->processType)
    {
    case VERTICAL:
        //Define the range in which calculations will occour
        ends[0] = 0;
        ends[1] = myData->dimensions[WIDTH];
        ends[2] = 0;
        ends[3] = myData->dimensions[HEIGHT] - MULTIPLICATION_LENGTH ;

        //Compute the product
        for (int i = ends[0]; i < ends[1]; i++)
        {
            for (int j = ends[2]; j < ends[3]; j++)
            {
                tempProd = 1;   //Create the indentity multiple
                for (int m = 0; m < MULTIPLICATION_LENGTH; m++)
                {
                    tempProd *= myData->numbers[i][j + m];
                }

                if (tempProd > myData->processResult)
                {
                    myData->processResult = tempProd;
                }
            }
        }
        break;
    case HORIZONTAL:
        ends[0] = 0;
        ends[1] = myData->dimensions[WIDTH] - MULTIPLICATION_LENGTH;
        ends[2] = 0;
        ends[3] = myData->dimensions[HEIGHT];

        //Compute the product

        for (int i = ends[0]; i < ends[1]; i++)
        {
            for (int j = ends[2]; j < ends[3]; j++)
            {
                tempProd = 1;   //Create the indentity multiple
                for (int m = 0; m < MULTIPLICATION_LENGTH; m++)
                {
                    tempProd *= myData->numbers[i + m][j];
                }

                if (tempProd > myData->processResult)
                {
                    myData->processResult = tempProd;
                }
            }
        }
        break;
    case FORWARD_DIAGONAL:
        ends[0] = 0;
        ends[1] = myData->dimensions[HEIGHT] - MULTIPLICATION_LENGTH ;
        ends[2] = 0;
        ends[3] = myData->dimensions[WIDTH] - MULTIPLICATION_LENGTH ;
        //Compute the product

        for (int i = ends[0]; i < ends[1]; i++)
        {
            for (int j = ends[2]; j < ends[3]; j++)
            {
                tempProd = 1;   //Create the indentity multiple
                for (int m = 0; m < MULTIPLICATION_LENGTH; m++)
                {
                    tempProd = tempProd * myData->numbers[i + m][j + m];

                }

                if (tempProd > myData->processResult)
                {
                    myData->processResult = tempProd;
                }
            }
        }
        break;
    case BACKWARDS_DIAGONAL:
        ends[0] = MULTIPLICATION_LENGTH - 1;
        ends[1] = myData->dimensions[HEIGHT];
        ends[2] = MULTIPLICATION_LENGTH - 1;
        ends[3] = myData->dimensions[WIDTH];
        //Compute the product

        for (int i = ends[0]; i < ends[1]; i++)
        {
            for (int j = ends[2]; j < ends[3]; j++)
            {
                tempProd = 1;   //Create the indentity multiple
                for (int m = 0; m < MULTIPLICATION_LENGTH; m++)
                {
                    tempProd *= myData->numbers[i - m][j - m];

                }

                if (tempProd > myData->processResult)
                {
                    myData->processResult = tempProd;
                }
            }
        }
        break;
    default:
        std::cerr << "Error in processing the products";
        break;
    }
    pthread_exit(NULL);

}

