FROM payara/server-full:5.2022.5

WORKDIR /opt/payara

# Substitui o domain.xml com configurações automáticas (JNDI, JDBC, etc.)
COPY domain.xml /opt/payara/appserver/glassfish/domains/domain1/config/domain.xml

# Copia driver do PostgreSQL
COPY drivers/postgresql-42.7.5.jar /opt/payara/appserver/glassfish/lib/

# WAR da aplicação será auto-deployed
COPY deploy/CircPeticionario.war /opt/payara/appserver/glassfish/domains/domain1/autodeploy/

EXPOSE 8080 4848

ENTRYPOINT ["/opt/payara/appserver/bin/asadmin"]
CMD ["start-domain", "--debug", "--verbose"]