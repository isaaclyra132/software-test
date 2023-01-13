package academico;

public enum StatusAprovacao {
	REPF {
		@Override
		public String mensagem() {
			return "Reprovado por não atender os critérios de assiduidade.";
		}

		@Override
		public String descricao() {
			return "Reprovado por Faltas";
		}
	},
	REP {
		@Override
		public String mensagem() {
			return "Reprovado porque a média foi inferior a três.";
		}

		@Override
		public String descricao() {
			return "Reprovado por Média";
		}
	},
	REC {
		@Override
		public String mensagem() {
			return "Aluno que fará a reposição.";
		}

		@Override
		public String descricao() {
			return "Em Recuperação";
		}
	},
	APR {
		@Override
		public String mensagem() {
			return "Aluno aprovado com média maior ou igual a sete.";
		}

		@Override
		public String descricao() {
			return "Aprovado por Média";
		}
	},
	APRN {
		@Override
		public String mensagem() {
			return "Aluno com média entre cinco e sete e que não tirou nenhuma nota inferior a três.";
		}

		@Override
		public String descricao() {
			return "Aprovado por Notas";
		}
	},
	REMF {
		@Override
		public String mensagem() {
			return "Reprovado porque a média foi inferior a três e também por não atender os critérios de assiduidade.";
		}

		@Override
		public String descricao() {
			return "Reprovado por Média e Faltas";
		}
	};

	public abstract String mensagem();
	
	public abstract String descricao();

}
