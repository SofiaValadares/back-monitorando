Link para os slides da apresentação: [link canva](https://www.canva.com/design/DAGlYph5x20/mHZp_OFfPFtCmBautac0cg/view?utm_content=DAGlYph5x20&utm_campaign=designshare&utm_medium=link2&utm_source=uniquelinks&utlId=h713ba88d5f) 

# Monitorando 
Repositório destinado ao projeto Monitorando, da disciplina de Requisitos, projeto de software e validação, do 5° período no CESAR School.

## Descrição do domínio
O Monitorando é uma plataforma universitária que promove o suporte acadêmico entre alunos, monitores e professores. 
Professores indicam monitores para disciplinas específicas e acompanham seu desempenho. Monitores, por sua vez, organizam seus atendimentos, 
registram relatos, tiram dúvidas e avaliam os alunos. Já os alunos podem agendar atendimentos, enviar dúvidas e avaliar os serviços recebidos. 
O sistema gera notificações automáticas e garante a integridade das interações com validações e restrições. Com isso, oferece um ambiente 
colaborativo e eficiente, focado na qualidade do atendimento acadêmico.<br>

Para uma descrição mais aprofundada, acesse nosso Domínio:
<a href="https://docs.google.com/document/d/1pr8iFta8g4Q8j4Iv-906MCFhbba3NpRgbT4N3jIPuz4/edit?usp=sharing" target="_blank">Descrição de Domínio</a>

## Padrões de Projeto utilizados
No desenvolvimento do nosso projeto, utilizamos os seguintes padrões de projetos:

## Como Rodar:

<table>
  <tr>- Clone o repositório do Backend em uma pasta:
  <dt>
    
      https://github.com/SofiaValadares/back-monitorando
      
  </dt>

  <tr>- Dentro de um terminal do Backend, rode:
  <dt>

      cd ./monitorando
      docker compose up -d --build
      
  </dt>

  <tr>- O backend já esta rodando dentro da porta 8080.<br>
  Obs: Certifique-se que não haja conflito de volumes ou contêineres. O nosso banco PostgreSQL roda na porta 5432.

  <tr>- Para desfazer os conteiners do docker rode:
  <dt>

       docker compose -v down
      
  </dt>
  
</table>

## Links do projeto
<a href="https://www.figma.com/design/zzKq3iU1rJI5zLDwKxiwG2/Prot%C3%B3tipo-de-Baixa?node-id=420-173&p=f&t=UeIwloLSmI3ndRo3-0" target="_blank">Protótipo de baixa</a><br>
<a href="https://www.figma.com/board/ZygQc3zs5MsBl5XGssUqqC/REQUISITOS---MONITORANDO?node-id=0-1&p=f&t=QGdAtu6hZsdM6Ngf-0" target="_blank">Mapa de histórias</a><br>
<a href="https://drive.google.com/drive/folders/1agzatOjMUu4EsqJzccTU_IvQOfrkF_1z?usp=sharing" target="_blank">Context Mapper</a><br>

## Nossos desenvolvedores
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/antonioz2022">
        <img src="https://avatars.githubusercontent.com/u/114232542?v=4" width="100px;" alt="Foto Antonio"/><br>
        <sub>
          <b>Antonio Albuquerque</b>
        </sub>
      </a>
      <br>
      <sub>aaon@cesar.school</sub>
    </td>
    <td align="center">
      <a href="https://github.com/dan-albuquerque">
        <img src="https://avatars.githubusercontent.com/u/114592376?v=4" width="100px;" alt="Foto Danilo"/><br>
        <sub>
          <b>Danilo Albuquerque</b>
        </sub>
      </a>
      <br>
      <sub>dam@cesar.school</sub>
    </td>
    <td align="center">
      <a href="https://github.com/guiga-sa">
        <img src="https://avatars.githubusercontent.com/u/123979639?v=4" width="100px;" alt="Foto Guilherme"/><br>
        <sub>
          <b>Guilherme Silveira</b>
        </sub>
      </a>
      <br>
      <sub>gsa3@cesar.school</sub>
    </td>
    <td align="center">
      <a href="https://github.com/P4d1lh4">
        <img src="https://avatars.githubusercontent.com/u/99270875?v=4" width="100px;" alt="Foto Arthur"/><br>
        <sub>
          <b>Arthur Padilha</b>
        </sub>
      </a>
      <br>
      <sub>app2@cesar.school</sub>
    </td>
    <td align="center">
      <a href="https://github.com/Henrique-12345">
        <img src="https://avatars.githubusercontent.com/u/133684535?v=4" width="100px;" alt="Foto Henrique"/><br>
        <sub>
          <b>Henrique Magalhães</b>
        </sub>
      </a>      
      <br>
      <sub>hlm2@cesar.school</sub>
    </td>
  </tr>
</table>
<table>
  <tr>
    <td align="center">
      <a href="https://github.com/LuizaCalife">
        <img src="https://avatars.githubusercontent.com/u/109395661?v=4" width="100px;" alt="Foto Calife"/><br>
        <sub>
          <b>Maria Luiza Calife</b>
        </sub>
      </a>
      <br>
      <sub>mlcdf@cesar.school</sub>
    </td>
  <td align="center">
      <a href="https://github.com/paulohcportella">
        <img src="https://avatars.githubusercontent.com/u/125464223?v=4" width="100px;" alt="Foto Paulo"/><br>
        <sub>
          <b>Paulo Portella</b>
        </sub>
      </a>
      <br>
      <sub>phcp@cesar.school</sub>
    </td>
    <td align="center">
      <a href="https://github.com/SofiaValadares">
        <img src="https://avatars.githubusercontent.com/u/113111708?v=4" width="100px;" alt="Foto Sofia"/><br>
        <sub>
          <b>Sofia Valadares</b>
        </sub>
      </a>
      <br>
      <sub>svc3@cesar.school</sub>
    </td>
    <td align="center">
      <a href="https://github.com/Malucoimbr">
        <img src="https://avatars.githubusercontent.com/u/98840187?v=4" width="100px;" alt="Foto Maria Luiza"/><br>
        <sub>
          <b>Maria Luísa Coimbra </b>
        </sub>
      </a>
      <br>
      <sub>mlcl@cesar.school</sub>
    </td>
  </tr>
</table>
<br>
