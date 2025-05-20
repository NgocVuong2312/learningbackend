from flask import Flask, request, render_template

import pyodbc

app= Flask(__name__)
def loaddata_fromdb(search_text):
    if search_text != " ":
        conn = pyodbc.connect(
        'DRIVER={SQL Server}; SERVER=DoS_bel; DATABASE=crime2024; Trusted_Connection=yes;'
        )
        cursor = conn.cursor()
        sql_command = """
            SELECT * FROM crime2024.dbo.Crime_Incidents_in_2024
            where SHIFT LIKE ?
        """
        cursor.execute(sql_command, ('%' + search_text + '%',))
        data = cursor.fetchall()
        conn.close()
        return data
@app.route("/")
def index():
    return render_template("cart.html")
@app.route('/searchData', methods=['POST'])
def search():
    search_text = request.form['input']
    datatb=loaddata_fromdb(search_text)
    return render_template ('cart.html', search_txt = search_text, table=datatb )
if __name__=='__main__':
    app.run(debug=True)