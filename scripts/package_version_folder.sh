#!/usr/bin/env bash
set -euo pipefail

VERSION_ID="1.21.1-client-mod"
OUT_DIR="client-mod/${VERSION_ID}"

mkdir -p "${OUT_DIR}"

JAR_SRC="$(ls -1 build/libs/*.jar 2>/dev/null | head -n 1 || true)"
if [[ -z "${JAR_SRC}" ]]; then
  echo "[ERR] Не найден jar в build/libs. Сначала собери проект." >&2
  exit 1
fi

cp "${JAR_SRC}" "${OUT_DIR}/${VERSION_ID}.jar"

echo "[OK] Готово: ${OUT_DIR}/${VERSION_ID}.jar"
echo "Теперь скопируй папку ${OUT_DIR} в ~/.minecraft/versions/"
