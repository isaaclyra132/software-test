package academico;

public enum StatusAprovacao {
	REPF {
		@Override
		public String getMessage() {
			return "Reprovado por não atender os critérios de assiduidade.";
		}

		@Override
		public String getDescription() {
			return "Reprovado por Faltas";
		}
	},
	REP {
		@Override
		public String getMessage() {
			return "Reprovado porque a média foi inferior a três.";
		}

		@Override
		public String getDescription() {
			return "Reprovado por Média";
		}
	},
	REC {
		@Override
		public String getMessage() {
			return "Aluno que fará a reposição.";
		}

		@Override
		public String getDescription() {
			return "Em Recuperação";
		}
	},
	APR {
		@Override
		public String getMessage() {
			return "Aluno aprovado com média maior ou igual a sete.";
		}

		@Override
		public String getDescription() {
			return "Aprovado por Média";
		}
	},
	APRN {
		@Override
		public String getMessage() {
			return "Aluno com média entre cinco e sete e que não tirou nenhuma nota inferior a três.";
		}

		@Override
		public String getDescription() {
			return "Aprovado por Notas";
		}
	},
	REMF {
		@Override
		public String getMessage() {
			return "Reprovado porque a média foi inferior a três e também por não atender os critérios de assiduidade.";
		}

		@Override
		public String getDescription() {
			return "Reprovado por Média e Faltas";
		}
	};

	public abstract String getMessage();
	
	public abstract String getDescription();

}
