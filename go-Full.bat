@echo off
echo off

set ContainerBinary=docker
rem set ContainerBinary=podman

set ComposeFile=CircPeticionario-Compose.yaml
set ProjectName=circ_peticionario

cd CircPeticionario-WebApp
	call go-BuildWithClean.bat
cd ..

rem A rede e gerida automaticamente pelo Compose; nao e necessario cria-la manualmente.
rem O frontend e empacotado numa imagem Nginx (FrontEnd/Dockerfile); use --build para incluir alteracoes aos ficheiros estaticos e configuracao.

%ContainerBinary% compose -f %ComposeFile% -p %ProjectName% up --build -d

pause
