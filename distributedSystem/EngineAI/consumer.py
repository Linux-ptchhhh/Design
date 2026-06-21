import json
import numpy as np
from kafka import KafkaConsumer
from sklearn.linear_model import SGDRegressor
from sklearn.preprocessing import PolynomialFeatures

model = SGDRegressor(
    penalty="l2",
    alpha=0.001,
    learning_rate='constant',
    eta0=0.01
)

poly = PolynomialFeatures(degree=2, include_bias=False)

model_initialized = False

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

            X = np.array(data["time"]).reshape(-1, 1)
            y = np.array(data["usages"])

            X_poly = poly.fit_transform(X)

            # Predict only if model already trained
            if model_initialized:

                pred = model.predict(X_poly)

                error = np.abs(y - pred)

                threshold = 20

                for actual, predicted, err in zip(y, pred, error):

                    if err > threshold:
                        print(
                            f"ALERT! "
                            f"Actual={actual}, "
                            f"Predicted={predicted:.2f}, "
                            f"Error={err:.2f}"
                        )

            # Train/update model
            model.partial_fit(X_poly, y)

            model_initialized = True

    except KeyboardInterrupt:
        pass

    finally:
        consumer.close()


if __name__ == "__main__":
    kafkaconsumer()
