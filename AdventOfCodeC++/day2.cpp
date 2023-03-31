#include <iostream>
#include <fstream>
#include <string>
#include <map>
using namespace std;
int main(){
    ifstream f("day2.txt");
    string line;
    map<string, int> my_map = {
    { "A X", 4 },
    { "A Y", 8 },
    { "A Z", 3 },
    { "B X", 1},
    { "B Y", 5},
    { "B Z", 9},
    { "C X", 7},
    { "C Y", 2},
    { "C Z", 6}
};
    int total = 0;
    while (getline(f, line)){
        total+= my_map[line];
    }
    cout << total << endl;
    return 0;
}
