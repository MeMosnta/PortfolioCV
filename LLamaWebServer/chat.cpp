#include "ggml.h"

#include "crow.h"

#include "utils.h"
#include <cstdio>
#include <cassert>
#include <cmath>
#include <cstdio>
#include <cstring>
#include <fstream>
#include <map>
#include <string>
#include <vector>
#include <iostream>
#include <signal.h>
#include <unistd.h>
#include <signal.h>



    


int main(int argc, char ** argv) {
    gpt_vocab vocab;
    llama_model model;
    gpt_params params;
    gpt_params_parse(params);
    llama_model_load(params.model, model, vocab, params.n_ctx);
    std::string MADEUP= "";
    crow::SimpleApp app;

    CROW_ROUTE(app, "/")([](){
        return "<html><body><form action = \"/next\" method = \"POST\"><input type = \"text\" name = \"prompt\"><input type = \"submit\"></form></body></html>";
    });
    CROW_ROUTE(app,"/next").methods("POST"_method)([&model, &vocab, &params](const crow::request& req){
        std::string prompt= req.body.substr(req.body.find("=")+1);
        std::string answer = Generate(prompt, model,vocab, params);
        return "<html><body><h1>" + answer + "</h1></body></html>";
        
    });
    app.port(18080).multithreaded().run();

    // MADEUP = Generate("what is the colour of the sun");
    // std::cout << "OUTPUT GENERATEED : " << MADEUP;
    return 0;
}
