#!/bin/bash

mariadb -u root -D $MARIADB_DATABASE < /database/ddl/sequence/sequence.sql

for file in /database/ddl/*.sql
do
  mariadb -u root -D $MARIADB_DATABASE < ${file}
done

#for file in /database/dml/*.sql
#do
#  mariadb -u root -D $MARIADB_DATABASE < ${file}
#done

#for file in /database/function/*.sql
#do
#  psql -U "$POSTGRES_USER" -d "$POSTGRES_DB" -a -f ${file}
#done

#for file in /database/index/*.sql
#do
#  mariadb -u root -D $MARIADB_DATABASE < ${file}
#done
