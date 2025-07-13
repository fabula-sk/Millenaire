import json, sys, re
MODID='millenaire'
translations={}
with open(sys.argv[1], encoding='utf-8') as f:
    for line in f:
        line=line.strip()
        if not line or line.startswith('#'):
            continue
        if '=' not in line:
            continue
        key,val=line.split('=',1)
        key=key.strip()
        val=val.strip()
        if key.startswith('tile.'):
            name=key[len('tile.'):] 
            if name.endswith('.name'):
                name=name[:-5]
            key=f"block.{MODID}.{name}"
        elif key.startswith('item.'):
            name=key[len('item.'):] 
            if name.endswith('.name'):
                name=name[:-5]
            key=f"item.{MODID}.{name}"
        translations[key]=val
json.dump(translations,sys.stdout,ensure_ascii=False,indent=4)
