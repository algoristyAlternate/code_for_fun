/*
	IIT2019038
	Device Specifications -
		Intel(R) Core(TM) i5-8265U CPU @ 1.60GHz
		RAM               :  8 GB
		CPU               :  8
		Threads per core  :  2

	Merge two sorted vectors
		Time is calculated using clock() function
	
	Method Used: Pthread

    User Inputs:
        size of arr1, elements of arr1
        size of arr2, elements of arr2
        Number of threads
*/

#include <bits/stdc++.h>
#include <pthread.h>

using namespace std;

int n, m; 
int t = 0, req;
vector<int> arr1, arr2;

vector<int> res;

int numOfThreads;

struct data_structure{
    vector<int> one;
    vector<int> two;
};

// merge function to merge the elements
void *p_merge(void *str){
    struct data_structure *cur_data_structure = (struct data_structure*)str;
    vector<int> left = cur_data_structure -> one;
    vector<int> right = cur_data_structure -> two;
    
    int l = 0, r = 0;
    while(l < left.size() or r < right.size()){
        if(l < left.size() and r < right.size()){
            if(left[l] < right[r])
                res[t++] = left[l++];
            else
                res[t++] = right[r++];
        }
        else if(l < left.size())
            res[t++] = left[l++];
        else
            res[t++] = right[r++];
    }
}

void solve(int num){
    if (n > m) {
        // cout<<" swapping occurs \n";
        swap(arr1, arr2);
        swap(n, m);
    }

    vector<pair<int, int>> v;

    int cnt = 1;
    while(cnt <= numOfThreads){
        int k = cnt * num;
        if(cnt == numOfThreads)
            k += req;
        
        int low = max(0, k - m), high = min(k, n);
        while(low <= high) {
            // num of elements to be taken from arr1
            int from_one = (low + high) >> 1; 
            // num of elements to be taken from arr2
            int from_two = k - from_one; 
            
            int l1 = INT_MIN, l2 = INT_MIN;
            int r1 = INT_MAX, r2 = INT_MAX;

            if(from_one != 0) l1 = arr1[from_one - 1];
            if(from_two != 0) l2 = arr2[from_two - 1];
            if(from_one != n) r1 = arr1[from_one];
            if(from_two != m) r2 = arr2[from_two];

            // condititon when partition occurs
            if(l1 <= r2 && l2 <= r1) {
                from_one--, from_two--;
                v.push_back({from_one, from_two});
                
                break;
            }
            else if (l1 > r2)
                high = from_one - 1;
            else
                low = from_one + 1;
        }
        cnt++;
    }
    
    for(auto c: v){
        cout<<c.first<<" "<<c.second<<endl;
    }

    pthread_t thread[numOfThreads];

    int preX = -1, preY = -1;
    for(int i = 0; i < v.size(); i++){
        vector<int> left, right;
        for(int k = preX + 1; k <= v[i].first; k++)
            left.push_back(arr1[k]);
        for(int k = preY + 1; k <= v[i].second; k++)
            right.push_back(arr2[k]);

        // cout<<"Left array is:   ";
        // for(auto c: left)cout<<c<<" ";
        // cout<<"\nRight array is:   ";
        // for(auto c: right)cout<<c<<" ";
        // cout<<"\n";

        struct data_structure *str = (struct data_structure*) malloc(sizeof(struct data_structure));
        str -> one = left;
        str -> two = right;

        pthread_create(&thread[i], NULL, p_merge, (void *)str);
        pthread_join(thread[i], NULL);

        preX = v[i].first;
        preY = v[i].second;
    }
}

int main(){
    cout << "The size of array 1 is: ";
    cin >> n;
    arr1.resize(n);
    cout << "Enter array elements: ";
    for(int i = 0; i < n; i++) cin >> arr1[i];

    cout << "The size of array 2 is: ";
    cin >> m;
    cout << "Enter array elements: ";
    arr2.resize(m);
    for(int i = 0; i < m; i++) cin >> arr2[i];
    
    res.resize(n+m);
    cout << "The number of threads are: ";
    cin >> numOfThreads;

    int num = (n + m) / numOfThreads;
    req = (n + m) % numOfThreads;

    // starting time
    clock_t begin = clock();

    solve(num);

    for(auto &s: res)cout<<s<<"  ";

    // ending time
	clock_t end = clock();
    // Time taken for complete execution
    double time_spent = (double)(end - begin) / CLOCKS_PER_SEC;
    printf("\nTime taken to multiply using pthread  :%.6f Seconds\n",time_spent);

    return 0;
}

/*
    PS C:\Users\ageofsagittarius\Desktop\Sem 7\PDC\IIT2019038_Assignment3> g++ .\parallel.cpp -pthread -o p
    PS C:\Users\ageofsagittarius\Desktop\Sem 7\PDC\IIT2019038_Assignment3> ./p.exe
    The size of array 1 is: 5
    Enter array elements: 1 2 3 4 5
    The size of array 2 is: 3
    Enter array elements: 1 2 3
    The number of threads are: 2
    1  1  2  2  3  3  4  5  
    Time taken to multiply using pthread  :0.002000 Seconds

    PS C:\Users\ageofsagittarius\Desktop\Sem 7\PDC\IIT2019038_Assignment3> ./p.exe
    The size of array 1 is: 20
    Enter array elements: 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20
    The size of array 2 is: 10
    Enter array elements: 1 2 3 4 5 6 7 8 9 9
    The number of threads are: 2
    1  1  2  2  3  3  4  4  5  5  6  6  7  7  8  8  9  9  9  10  11  12  13  14  15  16  17  18  19  20  
    Time taken to multiply using pthread  :0.004000 Seconds
*/