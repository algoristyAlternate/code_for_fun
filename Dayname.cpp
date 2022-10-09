#include<iostream>
using namespace std;
int main()
{
    int dayNo;
    cout<<"Enter Day NUmber:-";
    cin>>dayNo;
    switch (dayNo)
    {
    case 1:cout<<"Monday";
        break;
    case 2:cout<<"Tuesday";
        break;
    case 3:cout<<"wednesday";
        break;
    case 4:cout<<"Thursday";
        break;
    case 5:cout<<"friday";
        break;
    case 6:cout<<"Saturday";
        break;
    case 7:cout<<"Sunday";
        break;
    default:cout<<"Invalid";
        break;
    }
}