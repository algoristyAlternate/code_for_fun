#typedef ll long long

int isValid(vector<int> &arr, int B, int n, int m, ll mid){
    int student = 1;
    ll sum = 0;

    for(int i=0; i<n; i++){
        sum += arr[i];
        if(sum > mid){
            student++;
            sum = arr[i];
        }
    
    
        if(student > m)
            return false;
    }

    return student <= m;
}

int Solution::paint(int m, int B,vector<int> &arr) {
    int n = arr.size();

    int maxi = -1;
    ll sum = 0;
    for(int i=0; i<n; i++){
        maxi = max(maxi, arr[i]);
        sum += arr[i];
    }

    ll ans = -1;
    ll low = maxi, high = sum*B;
    while(low <= high){
        ll mid = low + (high - low)/2;
    
        if(isValid(arr, B, n, m, mid)){
            ans = mid;
            high = mid - 1;
        }   
        else{
            low = mid + 1;
        }
    }

    return (ans*B) % 10000003;
}