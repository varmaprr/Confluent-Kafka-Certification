# Kafka connectors

## Video tutorials:
* https://www.youtube.com/playlist?list=PLt1SIbA8guutTlfh0J7bGboW_Iplm6O_B

**what is a worker?**

 A single java process, it can be standalone or disctributed mode.

**Modes to run kafka connect server**

* Standalone mode
* Distributed mode - recommended to use production.

**What is standalone Mode**

* A single process runs your connectors and tasks.
* Configration is bundled with your process
* Very easy to started with, useful for development and testing.
* Not fault tolerant, no scalability, hard to monitor

**What is Distributed mode**

* Multiple workers run your connetors and tasks.
* Configuration is submitted using REST API.
* Easy to scale and fault tolerant(rebalance in case a worker dies)
* useful for production deployment of connectors.

**What is a task?**