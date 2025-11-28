#  Análise de Desempenho – Tabelas Hash  
**Autores:** Fernando Aschwanden, André Esteves, Gustavo Jansen  
**Disciplina:** Resolução de Problemas Estruturados em Computação

---

##  1. Descrição do Experimento

O objetivo deste experimento foi **medir o tempo de busca de elementos** em diferentes implementações de **tabelas hash**:

- **Linear**
- **Duplo Hashing**
- **Encadeamento Separado**

Para cada estrutura, foram testadas combinações de **tamanho da tabela** e **quantidade de dados inseridos**, com diferentes **fatores de carga** (α).  
Além disso, foram coletadas métricas adicionais, como:
- número de **colisões**,  
- **maiores listas encadeadas** (para o encadeamento separado),  
- **menor, maior e média de gaps** (distância entre posições ocupadas).

---

##  2. Resultados Experimentais

# Hash Linear
```
Tabela Hash	Tamanho Tabela	Tamanho Dados	Tempo de Inserção (ms)	Colisões	Fator de Carga	Tempo de Busca (ms)	3 Maiores Listas	Menor Gap	Maior Gap	Gap Médio
Linear (Multiplicação)	100.000	100.000	785	15.177.208	1,000	803	-	0	1	0,00
Linear (Multiplicação)	1.000.000	100.000	24	5.548	0,100	18	-	0	111	9,00
Linear (Multiplicação)	1.000.000	1.000.000	31.150	268.426.680	0,999	31.060	-	0	6	0,00
Linear (Multiplicação)	10.000.000	100.000	19	486	0,010	13	-	0	1.266	99,01
Linear (Multiplicação)	10.000.000	1.000.000	137	55.678	0,100	152	-	0	136	9,00
Linear (Multiplicação)	10.000.000	10.000.000	150.515	1.009.010.951	0,995	143.157	-	0	10	0,01
Linear (Misto)	100.000	100.000	898	15.569.587	1,000	952	-	0	1	0,00
Linear (Misto)	1.000.000	100.000	6	5.546	0,100	11	-	0	137	9,00
Linear (Misto)	1.000.000	1.000.000	65.696	561.516.472	1,000	66.474	-	0	6	0,00
Linear (Misto)	10.000.000	100.000	17	550	0,010	15	-	0	1.143	99,00
Linear (Misto)	10.000.000	1.000.000	195	55.433	0,100	155	-	0	124	9,00
Linear (Misto)	10.000.000	10.000.000	167.662	1.001.030.256	0,995	157.388	-	0	11	0,00
Linear (Divisão)	100.000	100.000	2.145	33.762.107	1,000	1.990	-	0	2	0,00
Linear (Divisão)	1.000.000	100.000	6	5.507	0,100	12	-	0	117	9,00
Linear (Divisão)	1.000.000	1.000.000	66.378	472.136.940	1,000	61.188	-	0	5	0,00
Linear (Divisão)	10.000.000	100.000	13	522	0,010	17	-	0	1.184	99,00
Linear (Divisão)	10.000.000	1.000.000	149	55.178	0,100	153	-	0	139	9,01
Linear (Divisão)	10.000.000	10.000.000	174.852	1.118.901.037	0,995	170.163	-	0	10	0,01
```

# Hash Duplo
```
Tabela Hash	Tamanho Tabela	Tamanho Dados	Tempo de Inserção (ms)	Colisões	Fator de Carga	Tempo de Busca (ms)	3 Maiores Listas	Menor Gap	Maior Gap	Gap Médio
Duplo (Multiplicação)	100.000	100.000	199	842.637	1,000	239	-	0	1	0,00
Duplo (Multiplicação)	1.000.000	100.000	17	5.371	0,100	21	-	0	106	9,00
Duplo (Multiplicação)	1.000.000	1.000.000	1.562	6.681.233	1,000	1.344	-	0	1	0,00
Duplo (Multiplicação)	10.000.000	100.000	12	519	0,010	13	-	0	1.245	99,00
Duplo (Multiplicação)	10.000.000	1.000.000	121	53.487	0,100	136	-	0	134	9,01
Duplo (Multiplicação)	10.000.000	10.000.000	12.352	43.163.548	0,995	11.751	-	0	3	0,00
Duplo (Misto)	100.000	100.000	163	815.922	1,000	154	-	0	1	0,00
Duplo (Misto)	1.000.000	100.000	28	5.178	0,100	18	-	0	121	9,00
Duplo (Misto)	1.000.000	1.000.000	1.815	6.629.453	1,000	1.549	-	0	1	0,00
Duplo (Misto)	10.000.000	100.000	24	508	0,010	17	-	0	1.172	99,00
Duplo (Misto)	10.000.000	1.000.000	171	53.430	0,100	159	-	0	142	9,01
Duplo (Misto)	10.000.000	10.000.000	14.513	43.106.286	0,995	12.425	-	0	3	0,01
Duplo (Divisão)	100.000	100.000	97	910.506	1,000	102	-	0	1	0,00
Duplo (Divisão)	1.000.000	100.000	7	5.286	0,100	13	-	0	126	9,00
Duplo (Divisão)	1.000.000	1.000.000	1.413	6.594.573	0,999	1.380	-	0	1	0,00
Duplo (Divisão)	10.000.000	100.000	10	482	0,010	15	-	0	1.146	99,00
Duplo (Divisão)	10.000.000	1.000.000	115	53.640	0,100	143	-	0	125	9,01
Duplo (Divisão)	10.000.000	10.000.000	12.759	43.178.498	0,995	11.970	-	0	3	0,00
```

# Encadeamento
```
Tabela Hash	Tamanho Tabela	Tamanho Dados	Tempo de Inserção (ms)	Colisões	Fator de Carga	Tempo de Busca (ms)	3 Maiores Listas	Menor Gap	Maior Gap	Gap Médio
Encadeamento (Multiplicação)	100.000	100.000	16	50.483	1,000	27	8,6,6	1	14	1,59
Encadeamento (Multiplicação)	100.000	1.000.000	200	5.005.646	10,000	1.208	24,24,23	1	2	1,00
Encadeamento (Multiplicação)	100.000	10.000.000	1.169	500.045.827	100,000	75.035	142,142,141	1	1	1,00
Encadeamento (Multiplicação)	1.000.000	100.000	17	4.987	0,100	19	3,3,3	1	164	10,51
Encadeamento (Multiplicação)	1.000.000	1.000.000	162	500.110	1,000	272	7,7,7	1	14	1,58
Encadeamento (Multiplicação)	1.000.000	10.000.000	1.910	50.032.380	10,000	12.764	27,26,26	1	2	1,00
Encadeamento (Multiplicação)	10.000.000	100.000	22	501	0,010	19	2,1,1	1	946	100,50
Encadeamento (Multiplicação)	10.000.000	1.000.000	242	50.425	0,100	215	3,3,3	1	132	10,51
Encadeamento (Multiplicação)	10.000.000	10.000.000	2.560	5.034.872	1,000	3.928	9,8,8	1	18	1,59
Encadeamento (Misto)	100.000	100.000	7	50.433	1,000	20	7,7,6	1	12	1,59
Encadeamento (Misto)	100.000	1.000.000	57	4.998.888	10,000	1.111	26,24,24	1	2	1,00
Encadeamento (Misto)	100.000	10.000.000	970	500.026.042	100,000	86.144	148,144,144	1	1	1,00
Encadeamento (Misto)	1.000.000	100.000	16	5.074	0,100	18	3,3,3	1	114	10,52
Encadeamento (Misto)	1.000.000	1.000.000	292	499.455	1,000	302	8,7,7	1	14	1,58
Encadeamento (Misto)	1.000.000	10.000.000	2.003	50.018.043	10,000	14.834	26,26,26	1	2	1,00
Encadeamento (Misto)	10.000.000	100.000	19	536	0,010	20	2,2,2	1	1.352	100,53
Encadeamento (Misto)	10.000.000	1.000.000	230	50.455	0,100	226	4,4,3	1	134	10,51
Encadeamento (Misto)	10.000.000	10.000.000	2.670	5.038.347	1,000	4.008	10,9,9	1	16	1,59
Encadeamento (Divisão)	100.000	100.000	6	49.678	1,000	22	6,6,6	1	16	1,58
Encadeamento (Divisão)	100.000	1.000.000	96	4.999.072	10,000	1.159	25,24,23	1	2	1,00
Encadeamento (Divisão)	100.000	10.000.000	783	500.029.979	100,000	95.541	143,142,141	1	1	1,00
Encadeamento (Divisão)	1.000.000	100.000	16	5.159	0,100	18	3,3,3	1	120	10,52
Encadeamento (Divisão)	1.000.000	1.000.000	156	500.562	1,000	301	8,7,7	1	17	1,58
Encadeamento (Divisão)	1.000.000	10.000.000	1.974	50.038.046	10,000	12.544	28,27,27	1	2	1,00
Encadeamento (Divisão)	10.000.000	100.000	21	491	0,010	22	2,2,2	1	1.120	100,49
Encadeamento (Divisão)	10.000.000	1.000.000	285	50.145	0,100	257	3,3,3	1	135	10,51
Encadeamento (Divisão)	10.000.000	10.000.000	3.293	5.036.150	1,000	4.455	8,8,8	1	17	1,59
```
---

##  3. Gráficos Comparativos (Escala Logarítmica)

###  Tempo de Busca
![Tempo de Busca (log)](figs/tempo_busca_log.png)

---

###  Número de Colisões
![Colisões (log)](figs/colisoes_log.png)

---

###  Maiores Listas Encadeadas
![Listas Encadeadas](figs/maiores_listas.png)

---

###  Gaps Entre Elementos
![Gap Médio](figs/gap_medio_log.png)

---

##  4. Conclusões

- O desempenho é **fortemente impactado pelo fator de carga (α)**.  
- **Hash Duplo** apresentou o **melhor custo-benefício**, com tempos baixos e poucas colisões.  
- **Encadeamento Separado** teve **maior estabilidade** com α=1, enquanto a **Sondagem Linear** degradou fortemente.  
- Em cenários reais, **α ≤ 0.75** é recomendado para sondagem linear e hash duplo.  
- A **distribuição dos gaps** reflete diretamente a dispersão e eficiência da função hash.



##  5. Ferramentas Utilizadas

- **Java** – Implementação das tabelas hash  
- **Python (Pandas + Matplotlib)** – Análise e geração dos gráficos  

---


> *Trabalho desenvolvido para o RA de Estruturas de Dados, com ênfase na análise empírica de funções hash e estratégias de resolução de colisões.*


