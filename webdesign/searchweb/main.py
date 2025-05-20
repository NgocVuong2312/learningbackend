from flask import Flask, render_template, request


app:Flask = Flask (__name__)
app.template_folder = "template"
@app.route("/")
def index():
    return render_template("search.html",
                           search_text = "")

@app.route('/search', methods=['post'])
def search():
    #Get data from Request
    my_search_text = request.form['searchInput']
    html_table = load_data(my_search_text)
    return render_template('search.html',
                           search_text=my_search_text, table = html_table)

def load_data(search_text):
    import pandas as pd
    #Đọc dữ liệu từ csv và lọc 
    df = pd.read_csv("D:\learning code\webdesign\searchweb\gradedata.csv")
    dfx = df
    if search_text != "":
        #Hoặc là fname | lname
        dfx = df[(df["fname"] == search_text) |
                 (df["lname"] == search_text)]
        print(dfx)
    html_table = dfx.to_html(classes='data',
                             escape=False)
    return html_table

if __name__ == '__main__':
    app.run(debug=True)