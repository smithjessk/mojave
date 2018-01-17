# mojave

Get texts about how much of your day has passed. This tool assumes you're awake for 18 hours in the day.

## Setup

Set up a `mojave.json` file with the following keys:

```json
{
  "number": "your_verizon_number",
  "email": "your_email",
  "password": "your_password",
  "day_start_time": 27000
}
```

`day_start_time` is in seconds.

```
$ lein uberjar
$ java -jar target/uberjar/mojave-0.1.0-SNAPSHOT-standalone.jar mojave.json
```

And you should get a text!

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
