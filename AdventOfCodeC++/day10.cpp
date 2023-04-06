#include <fstream>
#include <string>
#include <iostream>
#include <vector>
#include <algorithm>
int main(){
    std::ifstream f("day10.txt");
    std::string line;
    int total = 1;
    int cycle = 0;
    int ttotal = 0;
    std::vector<int>target = {19,20,59,60,99,100,139,140,179,180,219,220};
    while(getline(f,line)){
        auto yy = std::find(target.begin(),target.end(), cycle);
        if(yy!=target.end()){
            std::cout << "TOTAL AT " << cycle << ":" << total << std::endl;
            if (cycle % 2 == 1){
                int tc = cycle +1;
                ttotal+=tc*total;
            }
        }
        if(line == "noop"){
            cycle++;
            
        }
        else{
            
            line = line.substr(5);
            int x = std::stoi(line);
            total+=x;
            std::cout << "total = " << total << ", cycle = " << cycle << std::endl;

            cycle+=2;
        }

    }
    std:: cout << ttotal;
    system("pause");
    return 0;
}