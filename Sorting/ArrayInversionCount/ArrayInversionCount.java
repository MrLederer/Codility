class Solution {
    public int solution(int[] A) {
        int result = sort(A);    
        if(result > 1000000000)
            return -1;
        else
            return result;
    }
        private int[] numbers;
        private int[] helper;
        private int number;

        public int sort(int[] values) {
                this.numbers = values;
                number = values.length;
                this.helper = new int[number];
                return modifiedMergesort(0, number - 1);
        }

        private int modifiedMergesort(int low, int high) {
                // check if low is smaller then high, if not then the array is sorted
                if (low < high) {
                        // Get the index of the element which is in the middle
                        int middle = low + (high - low) / 2;
                        //this is a modification for the question
                        int res = 0;
                        // Sort the left side of the array
                        res += modifiedMergesort(low, middle);
                        // Sort the right side of the array
                        res += modifiedMergesort(middle + 1, high);
                        // Combine them both
                        return (res + modifiedMerge(low, middle, high));
                }
                return 0;
        }

        private int modifiedMerge(int low, int middle, int high) {
                
                // Copy both parts into the helper array
                for (int i = low; i <= high; i++) {
                        helper[i] = numbers[i];
                }
                int res = 0;
                int i = low;
                int j = middle + 1;
                int k = low;
                // Copy the smallest values from either the left or the right side back
                // to the original array
                while (i <= middle && j <= high) {
                        if (helper[i] <= helper[j]) {
                                numbers[k] = helper[i];
                                i++;
                        } else {
                                res += (middle - i)+1;
                                numbers[k] = helper[j];
                                j++;
                        }
                        k++;
                }
                // Copy the rest of the left side of the array into the target array
                while (i <= middle) {
                        numbers[k] = helper[i];
                        k++;
                        i++;
                }
                return res;
        }
}

