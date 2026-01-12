# Deployment Environments & Tools (Aufgabe 1 & 2)

## Aufgabe 1: Softwarelösungen für Umgebungen

Basierend auf der Recherche empfehle ich folgende Zuteilung der Tools zu den Umgebungen:

### 1. Development Environment (Lokale Entwicklung)
**Empfohlenes Tool: Docker Compose**
- **Grund:** Docker Compose ermöglicht es, die gesamte Applikation (Frontend, Backend, Datenbank) mit einem einzigen Befehl (`docker-compose up`) lokal zu starten. Es ist leichtgewichtig und erfordert kein komplexes Cluster-Setup.
- **Vorteil:** Entwickler haben eine identische Umgebung, unabhängig vom Betriebssystem (Mac/Windows/Linux).

### 2. Testing Environment (CI/CD)
**Empfohlenes Tool: GitLab CI / GitHub Actions + Docker**
- **Grund:** Für automatisierte Tests eignen sich CI-Pipelines. Diese können Docker-Container hochfahren, um Integrationstests durchzuführen.
- **Vorteil:** Tests laufen isoliert und reproduzierbar bei jedem Commit.

### 3. Staging Environment (Vor-Produktion)
**Empfohlenes Tool: Kubernetes (K8s) + Terraform**
- **Grund:** Staging sollte der Produktion möglichst ähnlich sein. Kubernetes orchestriert die Container wie in der Produktion. Terraform provisioniert die Infrastruktur (Server, Netzwerke) als Code (IaC).
- **Vorteil:** Verhindert "It works on my machine"-Probleme bei Deployments.

### 4. Production Environment
**Empfohlenes Tool: Kubernetes + Terraform + Helm**
- **Grund:** Maximale Skalierbarkeit, Ausfallsicherheit (Self-Healing) und Rolling Updates ohne Downtime. Helm hilft beim Paketmanagement der Applikationen.

---

## Aufgabe 2: Praktische Umsetzung & Reflexion

Ich habe mich entschieden, das **Development Environment** mittels **Docker Compose** umzusetzen.

### Umsetzung
Dazu wurden folgende Dateien erstellt:
1.  `recipe-planner-backend/Dockerfile`: Containerisiert das Spring Boot Backend.
2.  `recipe-planner-fronend/Dockerfile`: Containerisiert das React Frontend.
3.  `docker-compose.yml`: Orchestriert beide Services und vernetzt sie.

### Reflexion / Fazit
Der Einsatz von Docker Compose für die lokale Entwicklung war sehr erfolgreich.
- **Positive Aspekte:** Das Setup war innerhalb einer Lektion machbar. Es eliminiert Probleme wie "Java-Version nicht gefunden" (wie wir es beim lokalen Start hatten), da alles im Container läuft.
- **Herausforderungen:** Das Bauen der Images dauert beim ersten Mal etwas länger. Hot-Reloading im Frontend-Container muss korrekt konfiguriert sein, damit Entwicklungsänderungen sofort sichtbar sind (im aktuellen Setup via Volume-Mounts lösbar).
- **Einsatzempfehlung:** Docker Compose ist ideal für Teams und Onboarding neuer Mitarbeiter, da man nur Docker installiert haben muss und sofort loslegen kann. Für den produktiven Betrieb würde ich jedoch auf Kubernetes wechseln, um bessere Skalierung und Monitoring-Möglichkeiten zu haben.
