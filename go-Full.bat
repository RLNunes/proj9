@echo off
echo off

set ContainerBinary=docker
rem set ContainerBinary=podman

set ComposeFile=CircPeticionario-Compose.yaml
set ProjectName=circ_peticionario
set Network=network_circ_peticionario

cd CircPeticionario-WebApp
	call go-BuildWithClean.bat
cd ..

%ContainerBinary% network rm %Network%

%ContainerBinary% network create %Network%

%ContainerBinary% compose -f %ComposeFile% -p %ProjectName% build

%ContainerBinary% compose -f %ComposeFile% -p %ProjectName% create

pause

%ContainerBinary% compose -f %ComposeFile% -p %ProjectName% start