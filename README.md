# xzeros

FIXME

## Getting Started

1. Start the application: `lein run`
    or 
    ```
    lein repl
    (-main)
    ```
2. Go to http://localhost:3701 to see: `Hello World!`
    or http://localhost:3701/about

## Configuration

To configure logging see config/logback.xml. By default, the app logs to stdout and logs/.
To learn more about configuring Logback, read its [documentation](http://logback.qos.ch/documentation.html).


## Developing your service

1. Start a new REPL: `lein repl`
2. Start your service in dev-mode: `(def dev-serv (run-dev))`
3. Connect your editor to the running REPL session.
   Re-evaluated code will be seen immediately in the service.

### [Docker](https://www.docker.com/) container support

1. Build an uberjar of your service: `lein uberjar`
2. Build a Docker image: `sudo docker build -t xzeros .`
3. Run your Docker image: `docker run -p 8080:8080 xzeros`

### [OSv](http://osv.io/) unikernel support with [Capstan](http://osv.io/capstan/)

1. Build and run your image: `capstan run -f "8080:8080"`

Once the image it built, it's cached.  To delete the image and build a new one:

1. `capstan rmi xzeros; capstan build`

## neanderthal
Macos MKL installation:
ln -s /opt/intel/lib/* /usr/local/lib
ln -s /opt/intel/mkl/lib/* /usr/local/lib 

## redis
download, extract, make, make test, 
src/redis-server redis.conf
src/redis-cli
src/redis-cli shutdown
src/redis-cli ping

## Links
* [Other examples](https://github.com/pedestal/samples)

