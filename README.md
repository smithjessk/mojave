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

I have my `mojave` set up with `cron`. E.g.,

` */10 8-23 * * * perl -le 'sleep rand 550' && java -jar $PATH_TO_JAR $PATH_TO_MOJAVE_DOT_JSON`

This job runs every 10 minutes between 8 AM and midnight (exclusive upper bound). The `perl` line is to randomly spread out the distribution of the texts. I used 550 seconds as opposed to 600 so that I don't cross the midnight boundary. This is hacky and I plan to fix it soon.

## License

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
