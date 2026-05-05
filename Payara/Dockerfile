FROM payara/server-full:5.2022.5

WORKDIR /opt/payara

# domain.xml
COPY Payara/domain.xml \
     /opt/payara/appserver/glassfish/domains/domain1/config/domain.xml

# driver PostgreSQL
COPY Payara/drivers/postgresql-42.7.5.jar \
     /opt/payara/appserver/glassfish/lib/

# WAR da aplicação
COPY CircPeticionario-WebApp/target/CircPeticionario.war \
     /opt/payara/appserver/glassfish/domains/domain1/autodeploy/

EXPOSE 8080 4848

ENTRYPOINT ["/opt/payara/appserver/bin/asadmin"]
CMD ["start-domain", "--debug", "--verbose"]