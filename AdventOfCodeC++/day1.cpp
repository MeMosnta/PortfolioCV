#include <iostream>
#include <vector>
#include <string>
#include <fstream>
#include <numeric>
using namespace std;

int part2(){
    int total = 0;
    vector<int> totals = {0,0,0};
    string line;
    ifstream f("day1.txt");
    while(getline(f,line)){
        if (line.empty()){
            for (int i = 0; i < totals.size(); i++){
                if (total > totals[i]){
                    totals[i] = total;
                    total = 0;
                    sort(totals.begin(), totals.end());
                    break;
                }
            }
            total = 0;
            continue;
        }
        else{
            total+=stoi(line);
        }
    }
    return accumulate(totals.begin(), totals.end(), 0);
}

int main(){
    string line;
    int total = 0;
    int temp = 0;
    ifstream f("day1.txt");
    while(getline (f,line)){
        if (line.empty()){
            if (total>temp){
                temp =total;
            }
            total = 0;
            continue;
        }

        total+=stoi(line);
    }
    f.close();
    //cout << temp << endl;
    cout << part2() << endl;
    return 0;
}