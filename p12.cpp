/*
    ProjectEuler Implementations in C
    (c) Daniel Park 2013

    ///////////////////////
    Problem 12

    The following iterative sequence is defined for the set of positive integers:

    n → n/2 (n is even)
    n → 3n + 1 (n is odd)

    Using the rule above and starting with 13, we generate the following sequence:

    13 → 40 → 20 → 10 → 5 → 16 → 8 → 4 → 2 → 1
    It can be seen that this sequence (starting at 13 and finishing at 1) contains 10 terms. Although it has not been proved yet (Collatz Problem), it is thought that all starting numbers finish at 1.

    Which starting number, under one million, produces the longest chain?
    ///////////////////////



    Exit Errors:

 */

#include <cstdlib>
#include <cstdio>
#include <iostream>
#include <vector>

//Program Constants
const unsigned int DEFAULT_NUM = 1.0e6;    //Default length of searchable space
const int NO_ENTRY = -1;                      //Macro for a space not having an entry

//Variables
int num[DEFAULT_NUM];

//Debug Switches
const bool DEBUG_PRINT_SEQUENCES = false;

using namespace std;

//Prototypes
long CollatzSequence(long n);

int main(int argc, char const *argv[])
{
    //Occupy the array with values.
    for (int i = 0; i < DEFAULT_NUM; i++)
    {
        num[i] = NO_ENTRY;
    }

    //Calculate the sequence for numbers below the param.
    int count = 0;
    for (int i = 2; i <= DEFAULT_NUM; i++)
    {
        count = 0;      //Reset the Count
        long currentPos = i;
        while (currentPos != 1)
        {
            currentPos = CollatzSequence(currentPos);

            //We should catch already calculated numbers here!
            if (currentPos < DEFAULT_NUM)
            {

                if (num[currentPos] != NO_ENTRY)
                {
                    count += num[currentPos] + 1;
                    break;
                }
            }

            else
            {
                count++;
            }
        }
        num[i] = count;

        if (DEBUG_PRINT_SEQUENCES)
        {
            cout << "Number " << i << " has a sequence count of " << num[i] << endl;
        }


    }
    //Calculate the index with the largest number
    int maxCount = 0;
    long maxStartingNum = 0;
    for (int i = 0; i < DEFAULT_NUM; i++)
    {
        if (num[i] > maxCount)
        {
            maxCount = num[i];
            maxStartingNum = i;
        }

    }

    cout << "The maximum steppage occours from " << maxStartingNum << " with " << maxCount << " hops." << endl;
}

/*
    This function assumes a pre-check for end of sequence has been made
 */
long CollatzSequence(long n)
{
    if (n % 2 == 0)     //Even
    {
        return (n / 2);
    }
    else                //Odd
    {
        return (3 * n + 1);
    }

}
