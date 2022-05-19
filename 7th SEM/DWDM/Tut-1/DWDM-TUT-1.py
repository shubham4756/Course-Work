import pandas as pd
import math
from copy import deepcopy
from pyod.models.hbos import HBOS

print("Imports loaded")

fname = "Histograms.csv"

df = pd.read_csv(fname)

# skewness analysis
print(f"Skewness\n{df.skew()}")
print("")
# kurtosis analysis
print(f"Kurtosis\n{df.kurt()}")