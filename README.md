## Akka BackOff Supervision example

I had a little trouble understanding the mechanics of the [Akka Backoff Supervisor](http://doc.akka.io/docs/akka/current/general/supervision.html)
so I decided to play around with it. Most notably, I found out that 

* the Backoff Supervisor forwards messages to the created child

I hope this project can help someone get there more quickly than I did!


## License

    Copyright 2016 Fabio Tiriticco / Fabway

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
