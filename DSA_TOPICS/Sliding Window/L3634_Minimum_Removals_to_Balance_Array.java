public class L3634_Minimum_Removals_to_Balance_Array {
    public int indexOfMinIntoKTimes(long target, int[] nums) {

        int i = 0;
        int j = nums.length - 1;

        int ans = -1;

        while (i <= j) {

            int mid = i + (j - i) / 2;

            if (nums[mid] <= target) {
                ans = mid;
                i = mid + 1;
            } else if (nums[mid] > target) {
                j = mid - 1;
            }

        }

        return ans;

    }

    public int minRemoval(int[] nums, int k) {

        if(nums.length==1) return 0;

        Arrays.sort(nums);
        int min = Integer.MAX_VALUE;

        for(int i=0; i<nums.length; i++){
            long currentMin = nums[i];
            long currentk = k;
            long target = currentMin*currentk;
            int j = indexOfMinIntoKTimes(target,nums);
            min = Math.min(min,nums.length-(j-i+1));
        }

        return min;

    }
}
