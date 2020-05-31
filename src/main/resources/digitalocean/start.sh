cd ScrappingService
git checkout master
git pull
mvn clean package
java -jar target/ScrappingService.jar &

cd ../MatchingService
git checkout master
git pull
mvn clean package
java -jar target/MatchingService.jar &

cd ../YouHockey
git checkout master
git pull
mvn clean package
java -jar target/YouHockey.jar &

cd ../JackNorthon
git checkout master
git pull
ng serve --host=161.35.70.99 &

