
import sys
import os
from pathlib import Path

import pandas as pd
import matplotlib.pyplot as plt

def ensure_outdir(path: str) -> Path:
    p = Path(path)
    p.mkdir(parents=True, exist_ok=True)
    return p

def load_data(csv_path: str) -> pd.DataFrame:
    df = pd.read_csv(csv_path)
    # Normaliza strings (sem espaços extras)
    for col in ["hash_base", "estrategia"]:
        if col in df.columns:
            df[col] = df[col].astype(str).str.strip().str.upper()
    return df

def bar_group_by_strategy_per_N(df: pd.DataFrame, metric: str, outdir: Path, title_prefix: str):
    """
    Faz 3 gráficos (um por N): eixo X = estratégia, altura = média do metric.
    Salva PNGs em outdir.
    """
    for N, sub in df.groupby("N"):
        pivot = sub.groupby("estrategia", as_index=False)[metric].mean()
        pivot = pivot.sort_values("estrategia")
        plt.figure()
        plt.bar(pivot["estrategia"], pivot[metric])
        plt.title(f"{title_prefix} — N={N}")
        plt.xlabel("Estratégia")
        plt.ylabel(metric)
        plt.tight_layout()
        plt.savefig(outdir / f"{metric}_por_estrategia_N{N}.png", dpi=130)
        plt.close()

def collisions_by_strategy_per_N(df: pd.DataFrame, outdir: Path):
    bar_group_by_strategy_per_N(df, "colisoes", outdir, "Colisões (média por estratégia)")

def time_insert_by_strategy_per_N(df: pd.DataFrame, outdir: Path):
    bar_group_by_strategy_per_N(df, "tempo_inser_ms", outdir, "Tempo de Inserção (ms)")

def time_search_by_strategy_per_N(df: pd.DataFrame, outdir: Path):
    bar_group_by_strategy_per_N(df, "tempo_busca_ms", outdir, "Tempo de Busca (ms)")

def gaps_average_by_strategy(df: pd.DataFrame, outdir: Path):
    """
    Gráfico de linhas: avgGap médio por N, uma linha por estratégia.
    (Um gráfico por estratégia para manter 1 chart por figure conforme regra)
    """
    for strat, sub in df.groupby("estrategia"):
        gp = sub.groupby("N", as_index=False)["avgGap"].mean().sort_values("N")
        plt.figure()
        plt.plot(gp["N"], gp["avgGap"], marker="o")
        plt.title(f"GAP médio por N — Estratégia: {strat}")
        plt.xlabel("N")
        plt.ylabel("avgGap")
        plt.tight_layout()
        plt.savefig(outdir / f"avgGap_por_N_{strat}.png", dpi=130)
        plt.close()

def gaps_minmax_by_strategy(df: pd.DataFrame, outdir: Path):
    """
    Para cada estratégia, cria um gráfico com minGap e maxGap médios por N (duas curvas).
    Como a regra pede um gráfico por figure, geramos uma figura por estratégia com as duas séries.
    """
    for strat, sub in df.groupby("estrategia"):
        gp = sub.groupby("N", as_index=False)[["minGap","maxGap"]].mean().sort_values("N")
        plt.figure()
        plt.plot(gp["N"], gp["minGap"], marker="o", label="minGap")
        plt.plot(gp["N"], gp["maxGap"], marker="o", label="maxGap")
        plt.title(f"minGap / maxGap por N — Estratégia: {strat}")
        plt.xlabel("N")
        plt.ylabel("gap")
        plt.legend()
        plt.tight_layout()
        plt.savefig(outdir / f"minmaxGap_por_N_{strat}.png", dpi=130)
        plt.close()

def top3_lists_for_chaining(df: pd.DataFrame, outdir: Path):
    """
    Para a estratégia ENCADEAMENTO, encontra os 3 maiores valores (globais) de top1/top2/top3
    por N (usando o máximo entre combinações) e plota barras.
    """
    enc = df[df["estrategia"] == "ENCADEAMENTO"]
    if enc.empty:
        return
    # Para cada N, pegar os maiores top1/top2/top3 observados
    for N, sub in enc.groupby("N"):
        tops = {
            "top1_max": int(sub["top1"].max() if "top1" in sub else 0),
            "top2_max": int(sub["top2"].max() if "top2" in sub else 0),
            "top3_max": int(sub["top3"].max() if "top3" in sub else 0),
        }
        labels = ["top1", "top2", "top3"]
        values = [tops["top1_max"], tops["top2_max"], tops["top3_max"]]
        plt.figure()
        plt.bar(labels, values)
        plt.title(f"Maiores listas encadeadas (máximo) — N={N}")
        plt.xlabel("Lista")
        plt.ylabel("tamanho (nós)")
        plt.tight_layout()
        plt.savefig(outdir / f"top3_encadeamento_max_N{N}.png", dpi=130)
        plt.close()

def per_hashbase_time(df: pd.DataFrame, outdir: Path):
    """
    Gráficos adicionais: tempo médio de inserção e busca por base de hash (um gráfico por base),
    variando N e agregando estratégias (média).
    """
    for base, sub in df.groupby("hash_base"):
        gp_ins = sub.groupby("N", as_index=False)["tempo_inser_ms"].mean().sort_values("N")
        plt.figure()
        plt.plot(gp_ins["N"], gp_ins["tempo_inser_ms"], marker="o")
        plt.title(f"Tempo de Inserção (ms) por N — Base: {base}")
        plt.xlabel("N"); plt.ylabel("tempo_inser_ms")
        plt.tight_layout()
        plt.savefig(outdir / f"tempo_inser_por_N_base_{base}.png", dpi=130)
        plt.close()

        gp_bus = sub.groupby("N", as_index=False)["tempo_busca_ms"].mean().sort_values("N")
        plt.figure()
        plt.plot(gp_bus["N"], gp_bus["tempo_busca_ms"], marker="o")
        plt.title(f"Tempo de Busca (ms) por N — Base: {base}")
        plt.xlabel("N"); plt.ylabel("tempo_busca_ms")
        plt.tight_layout()
        plt.savefig(outdir / f"tempo_busca_por_N_base_{base}.png", dpi=130)
        plt.close()

def main():
    # Uso: python graficos_hash.py OUTPUT/resultados_experimentos.csv OUTPUT/plots
    if len(sys.argv) < 3:
        print("Uso: python main.py <caminho_csv_resultados> <pasta_saida_plots>")
        sys.exit(1)

    csv_path = sys.argv[1]
    out_dir = ensure_outdir(sys.argv[2])

    df = load_data(csv_path)

    # Geração dos gráficos principais
    time_insert_by_strategy_per_N(df, out_dir)
    time_search_by_strategy_per_N(df, out_dir)
    collisions_by_strategy_per_N(df, out_dir)
    gaps_average_by_strategy(df, out_dir)
    gaps_minmax_by_strategy(df, out_dir)
    top3_lists_for_chaining(df, out_dir)

    # Extras por base de hash (úteis para discussão)
    per_hashbase_time(df, out_dir)

    # Salvar também um resumo em CSV para anexar no relatório
    resumo = df.groupby(["N","estrategia"], as_index=False).agg({
        "tempo_inser_ms":"mean",
        "tempo_busca_ms":"mean",
        "colisoes":"mean",
        "avgGap":"mean",
        "minGap":"mean",
        "maxGap":"mean"
    })
    resumo.to_csv(out_dir / "resumo_por_N_estrategia.csv", index=False)

    print(f"Gráficos e resumo salvos em: {out_dir.resolve()}")

if __name__ == "__main__":
    main()
