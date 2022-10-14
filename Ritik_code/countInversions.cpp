  public:
    // arr[]: Input Array
    // N : Size of the Array arr[]
    // Function to count inversions in the array.
    
    long long ans = 0;
    
    void merge(long long arr[], long long l, long long mid, long long r){
        
        int n = mid - l + 1, m = r - mid;
        
        vector<long long> left(n), right(m);
        
        long long idx = l;
        for(long long i = 0; i < n; i++)
            left[i] = arr[idx++];
        for(long long i = 0; i < m; i++)
            right[i] = arr[idx++];
            
        int i = 0, j = 0;
        idx = l;
        
        while(i < n and j < m){
            if(right[j] < left[i]){
                arr[idx++] = right[j++];
                ans += (n - i);
            }
            else
                arr[idx++] = left[i++];
        }
        
        while(i < n)
            arr[idx++] = left[i++];
            
        while(j < m)
            arr[idx++] = right[j++];
    }
    
    void mergeSort(long long arr[], long long l, long long r){
        if(l >= r)
            return;
            
        long long mid = l + (r - l) / 2;
        
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        
        merge(arr, l, mid, r);
    }
    
    long long int inversionCount(long long arr[], long long n)
    {
        // Your Code Here
        
        mergeSort(arr, 0, n-1);
        
        return ans;
        
    }

};