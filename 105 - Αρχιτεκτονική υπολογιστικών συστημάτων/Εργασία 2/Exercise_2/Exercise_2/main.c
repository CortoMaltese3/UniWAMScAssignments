#include<stdio.h>

void main()
{
 //   int A[16] = { 1, 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9, 10, 11, 12, 13, 14, 15, 16 };
    printf("Hello world");

    int A[6] = { 1,2,3,4,5,6 };
    int i = 5;
    while (i >= 0)
    {
        A[i] = A[i] + i;
        i--;
    }


}