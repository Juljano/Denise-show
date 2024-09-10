# -*- coding: utf-8 -*-

import Adafruit_DHT

# Sensor-Typ
sensor = Adafruit_DHT.DHT22

# GPIO-Pin 4 on Raspberry PI
gpio_pin = 4

#read Temperature and Humidity
humidity, temperature = Adafruit_DHT.read_retry(sensor, gpio_pin)
# support Python 2.xx
if humidity is not None and temperature is not None:
    print('Temperatur: {:.2f}°C'.format(temperature))
    print('Luftfeuchtigkeit: {:.2f}%'.format(humidity))
else:
    print('failed to read the sensor')


