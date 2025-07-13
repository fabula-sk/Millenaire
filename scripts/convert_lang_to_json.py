import json
import re
import sys

MODID = "millenaire"


def to_snake(name: str) -> str:
    """Convert a camelCase or PascalCase string to snake_case."""
    parts = name.split('.')
    def snake(part):
        s1 = re.sub('(.)([A-Z][a-z]+)', r'\1_\2', part)
        s2 = re.sub('([a-z0-9])([A-Z])', r'\1_\2', s1)
        return s2.lower()
    return '.'.join(snake(p) for p in parts)


translations = {}
with open(sys.argv[1], encoding="utf-8") as f:
    for line in f:
        line = line.strip()
        if not line or line.startswith('#'):
            continue
        if '=' not in line:
            continue
        key, val = line.split('=', 1)
        key = key.strip()
        val = val.strip()
        if key.startswith('tile.'):
            name = key[len('tile.') :]
            if name.endswith('.name'):
                name = name[:-5]
            name = to_snake(name)
            key = f"block.{MODID}.{name}"
        elif key.startswith('item.'):
            name = key[len('item.') :]
            if name.endswith('.name'):
                name = name[:-5]
            name = to_snake(name)
            key = f"item.{MODID}.{name}"
        translations[key] = val

json.dump(translations, sys.stdout, ensure_ascii=False, indent=4)

