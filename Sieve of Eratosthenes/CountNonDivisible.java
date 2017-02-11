*/
Task description
You are given a non-empty zero-indexed array A consisting of N integers.

For each number A[i] such that 0 = i < N, we want to count the number of elements of the array that are not the divisors of A[i]. We say that these elements are non-divisors.

For example, consider integer N = 5 and array A such that:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 3
    A[4] = 6
For the following elements:

A[0] = 3, the non-divisors are: 2, 6,
A[1] = 1, the non-divisors are: 3, 2, 3, 6,
A[2] = 2, the non-divisors are: 3, 3, 6,
A[3] = 3, the non-divisors are: 2, 6,
A[4] = 6, there aren't any non-divisors.
Write a function:

class Solution { public int[] solution(int[] A); }

that, given a non-empty zero-indexed array A consisting of N integers, returns a sequence of integers representing the amount of non-divisors.

The sequence should be returned as:

a structure Results (in C), or
a vector of integers (in C++), or
a record Results (in Pascal), or
an array of integers (in any other programming language).
For example, given:

    A[0] = 3
    A[1] = 1
    A[2] = 2
    A[3] = 3
    A[4] = 6
the function should return [2, 4, 3, 2, 0], as explained above.

Assume that:

N is an integer within the range [1..50,000];
each element of array A is an integer within the range [1..2 * N].
Complexity:

expected worst-case time complexity is O(N*log(N));
expected worst-case space complexity is O(N), beyond input storage (not counting the storage required for input arguments).
Elements of input arrays can be modified.
*/
class Solution {
    public int[] solution(int[] A) 
    {
        int N = A.length;
        int[][] divCounter = getDivCounter(A,N);
        int[][] nonDivCounter =getNonDivCounter(divCounter,N);
        for(int i = 0;i<N;i++)
        {
            A[i] = nonDivCounter[A[i]][1];
        }
        return A;
    }
    private int[][] getNonDivCounter(int[][] countArray,int length)
    {
        for(int i=0;i<countArray.length ;i++)
        {
            countArray[i][1] = length - countArray[i][1];
        }
        return countArray;
    }
    private int[][] getDivCounter(int[] input,int length)
    {
        int[][] range = new int[2*length+1][2];
        for(int i = 0;i < length;i++)
        {
            range[input[i]][0]++;   // my counter to know how many dividers (can always divide my self)
            range[input[i]][1] = -1;// my flag to know this number is from input, and -1 because it was'nt checked;
        }
        for(int i = 0;i<length;i++)
        {
            if(range[input[i]][1]==-1)
            {
                range[input[i]][1]=0;
                for(int j =1;j*j<=input[i];j++)
                {
                    if(input[i]%j==0) 
                    {
                        range[input[i]][1]+= range[j][0];
                        if(input[i]/j!=j)
                            range[input[i]][1]+= range[input[i]/j][0];
                    
                    }
                }
            }
        }
        return range;
    }
}