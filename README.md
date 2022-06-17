## A chainlink node load test

### prerequisite:

* Java 8 or later

run the following commands to set required environment variables
```bash
export ADMIN_USER=<chainlink_admin_user>
export ADMIN_PASSWORD=<chainlink_admin_password>
```

you can set the target chainlink server by specifying the following variables

```bash
export CHAIN_LINK_HOST=<chainlink host, default is localhost>
export CHAIN_LINK_PORT=<chainlink server port, default is 6688>
export CHAIN_LINK_HTPP_PROTOCOL=<http/https , default is http>
```

you can first create the jobs that you are interested in testing via chainlink admin
console.

Then you can export those jobs (job ids) into a csv file by running:

```bash
export JOB_IDS_FILE=./job_ids.csv
./gradlew gatlingRun-chainlink.node.load.test.ListJobsSimulation
```

finally you can run the jobs by calling

```bash
./gradlew gatlingRun-chainlink.node.load.test.ExecuteJobsSimulation
```

This simulation uses all the job ids specified in the `JOB_IDS_FILE` csv file.

You can change the number of users running the test by specifying 
```bash
export USERS_COUNT=<users count>
```
