from flask import Flask, render_template, request, redirect,url_for, g
from spotifytest import *
import asyncio
app = Flask(__name__)

s = spotifytest()

@app.route('/')
def index():
    return render_template("index.html")

@app.route('/one', methods = ['GET','POST'])
def one():
    if request.method == 'GET':
        return render_template("one.html")
    elif request.method == 'POST':
        user = request.form.get("user")
        password = request.form.get("passwd")
        print(user, password)
        return redirect(url_for("index"))
@app.route('/two', methods = ['GET','POST'])
def two():
    
    return render_template("two.html")
@app.route('/spotifygame', methods = ['POST','GET'])
def lol():
    if request.method == 'GET':
        return render_template("spotify.html")
    else:
        s.randomplay()
        return render_template("spotify.html")


@app.route('/spotify/', methods = ['POST'])
def spotify():
    s.getauth()
    print(s.sp.current_user())
    s.getsongs(50)
    return redirect(url_for('lol'))

def main():
    app.run(host="0.0.0.0")

if __name__ == "__main__":
    main()