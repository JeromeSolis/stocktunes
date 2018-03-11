import stockreader
from pprint import pprint

symbol = "TSLA"
stat = "sl1d1t1c1ohgv"

quote = stockreader.get_all(symbol)
pprint(quote)
