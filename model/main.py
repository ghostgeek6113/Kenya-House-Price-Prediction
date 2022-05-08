import traceback
from flask import Flask, jsonify, request
import pickle
import pandas as pd


app = Flask(__name__)


@app.route('/predict', methods=['POST'])
def predict():
        try:
            json_ = request.json
            print(json_)
            print(type(json_))
            query = pd.DataFrame(json_)
            print(query)
            # query = query.reindex(columns=model_columns)
            # print(query)
            clf = pickle.load(open('model.pkl', 'rb'))
            model_columns = pickle.load(open('model_columns.pkl', 'rb'))
            prediction = list(clf.predict(query))
            return jsonify({'prediction': str(prediction)})

        except:

            return jsonify({'trace': traceback.format_exc()})


if __name__ == '__main__':
    print("model loaded")
    print("model columns loaded")
    app.run(debug=True, port=3000)
