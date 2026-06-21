import json
import numpy as np
from kafka import KafkaConsumer
from sklearn.linear_model import SGDClassifier
from sklearn.preprocessing import StandardScaler
from serverScript import dockerRun,dockerStop

model = SGDClassifier(loss='log_loss', random_state=42)

scaler = StandardScaler()

all_possible_classes = np.array([0, 1, 2])

model_initialized = False

def predictionAnalyse(value):
    if value == 1:
        dockerStop(specific_run=True)
   elif value == 2:
       print("sever shutdown start// *critical overload ......*")
       dockerStop()



def get_true_label(cpu_usage):

    if cpu_usage < 50:
        return 0
    elif cpu_usage < 80:
        return 1
    else:
        return 2


def kafkaconsumer():
     global model_initialized

    consumer = KafkaConsumer(
        'server-mtc',
        bootstrap_servers=['localhost:9092'],
        group_id='mtc',
        auto_offset_reset='earliest',
        value_deserializer=lambda x: json.loads(x.decode('utf-8'))
    )

    try:
        for message in consumer:

            data = message.value

            x = np.array(data["usages"])

            y_true = np.array([get_true_label(data)])

            scaler.partial_fit(x)

            X = scaler.transform(x)

            if model_initialized:

                model.partial_fit(X_scaled, y_true, classes=all_possible_classes)

                prediction = model.predict(X)[0]

                predictionAnalyse(prediction)

            model.partial_fit(X, y_true, classes=all_possible_classes)

            model_initialized = True

    except Exception:
        print("error occured in consumer of SDG")

if __name__ == "__main__":
    kafkacinsumer()
