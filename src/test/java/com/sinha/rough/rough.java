package com.sinha.rough;

public class rough {
	
	public static int binarySearch(int[] nums, int target, int l, int h)
    {
        if(h>=l)
        {
            int mid = l + (h-l)/2;

            if(nums[mid] == target)
            {
                return mid;
            }

            if(nums[mid] > target)
            {
                return binarySearch(nums, target, l, mid-1);
            }

            return binarySearch(nums, target, mid+1 , h);
        }

        return -1;
    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int[] nums = new int[] {1,2,3,4,5,6,6};
		System.out.println(binarySearch(nums, 6, 0, nums.length-1));

	}

}
