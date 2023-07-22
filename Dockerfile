FROM openjdk:13
EXPOSE 3000
ONBUILD COPY "./SpringBoot-Backend/mybank.account.transactions/target/mybank.account.transactions.demo-0.0.1-SNAPSHOT.jar" "mybank.account.transactions.demo-0.0.1-SNAPSHOT.jar"
ONBUILD CMD ["java","-jar","mybank.account.transactions.demo-0.0.1-SNAPSHOT.jar"]
ADD ${HTTPS} "true"
#ENTRYPOINT ["npm","start"]