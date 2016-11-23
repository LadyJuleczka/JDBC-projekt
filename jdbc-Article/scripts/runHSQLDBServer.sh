#!/bin/bash

java -cp /home/student/java/JDBC-projekt/jdbc-Article/hsqldb/lib/hsqldb.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb
