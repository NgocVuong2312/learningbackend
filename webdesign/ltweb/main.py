from flask import Flask,render_template,request
app = Flask(__name__)
app.template_folder="template"
@app.route('/')
def index():
    name="world"
    return render_template(template_name_or_list="index.html",username=name)
if __name__ == "__main__":
    app.run()