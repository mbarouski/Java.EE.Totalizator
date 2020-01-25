
.PHONY: start_environment
## start_environment: Starts third services required by the application
start_environment:
	@docker-compose up -d

.PHONY: stop_environment
## stop_environment: Stops third services required by the application
stop_environment:
	@docker-compose down

.PHONY: compile
## compile: Builds package
compile:
	@mvn package

.PHONY: run
## run: Run the application
run:
	@java -jar target/dependency/webapp-runner.jar --port 8888 target/*.war


# docker run --name some-mysql -e MYSQL_ROOT_PASSWORD=my-secret-pw -d mysql:tag