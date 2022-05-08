import json

import requests

# url = "https://midtermprojectapi.herokuapp.com/predict"
url = "http://127.0.0.1:3000/predict"
data = [{"city": "Nairobi", "area": "Muthaiga", "bedrooms": 10, "bathrooms": 10, "size": 10, "parking": 10}]
results = requests.post(url, json=data)
results_text = results.text
json_data = json.loads(results.text)
json_datatrial = json_data['prediction']
trial1 = json_datatrial.replace("[", " ")
trial1 = trial1.replace("]", " ")
print(trial1)
print(results_text)
print(json_data)
