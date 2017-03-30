package modelo;

import java.awt.Cursor;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controle.RotatedIcon;

public class Peca {
	private int valor1;
	private int valor2;
	public ImageIcon imagem;
	public int largura = 40;
	public int altura = 85;
	public int posicaoX;
	public int posicaoY;
	public boolean virada;
	
	public int rotacao;
	
	public Peca(int valor1, int valor2, boolean virada){
		this.valor1 = valor1;
		this.valor2 = valor2;
		this.virada = virada;
		imagem = new ImageIcon(".\\image\\peca\\peca" + valor1 + valor2 + ".png");
	}
	
	public void drawPeca(JPanel painel, int posicaoX, int posicaoY, int rotacao){
		this.posicaoX = posicaoX;
		this.posicaoY = posicaoY;
		// rotacao
		// 0 = posicao original		-	 O
		//								 X
		//
		// 1 = 90� para a direita	-	 XO
		//
		// 2 = 180� para a direita	-	 X
		//								 O
		//
		// 3 = 270� para a direita	-	OX
		//
		
		JLabel l = new JLabel();
		l.setName("" + valor1 + valor2);
		if (this.virada){
			if (rotacao == 0){
				l.setIcon(imagem);
				l.setBounds(this.posicaoX, this.posicaoY, this.largura, this.altura);
			} else if (rotacao == 1){
				RotatedIcon rimagem = new RotatedIcon(imagem, RotatedIcon.Rotate.DOWN);
				l.setIcon(rimagem);
				l.setBounds(this.posicaoX, this.posicaoY, this.altura, this.largura);
			} else if (rotacao == 2){
				RotatedIcon rimagem = new RotatedIcon(imagem, RotatedIcon.Rotate.UPSIDE_DOWN);
				l.setIcon(rimagem);
				l.setBounds(this.posicaoX, this.posicaoY, this.largura, this.altura);
			} else if (rotacao == 3){
				RotatedIcon rimagem = new RotatedIcon(imagem, RotatedIcon.Rotate.UP);
				l.setIcon(rimagem);
				l.setBounds(this.posicaoX, this.posicaoY, this.altura, this.largura);
			}
		} else {
			ImageIcon desvirada = new ImageIcon(".\\image\\peca\\pecaOff.png");
			if (rotacao == 0){
				l.setIcon(desvirada);
				l.setBounds(this.posicaoX, this.posicaoY, this.largura, this.altura);
			} else if (rotacao == 1){
				RotatedIcon rimagem = new RotatedIcon(desvirada, RotatedIcon.Rotate.DOWN);
				l.setIcon(rimagem);
				l.setBounds(this.posicaoX, this.posicaoY, this.altura, this.largura);
			} else if (rotacao == 2){
				RotatedIcon rimagem = new RotatedIcon(desvirada, RotatedIcon.Rotate.UPSIDE_DOWN);
				l.setIcon(rimagem);
				l.setBounds(this.posicaoX, this.posicaoY, this.largura, this.altura);
			} else if (rotacao == 3){
				RotatedIcon rimagem = new RotatedIcon(desvirada, RotatedIcon.Rotate.UP);
				l.setIcon(rimagem);
				l.setBounds(this.posicaoX, this.posicaoY, this.altura, this.largura);
			}
		}
		
		this.rotacao = rotacao;
		
		l.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		painel.add(l);
	}
	
	/**
	 * calcularPosicaoPeca() eh o metodo reponsavel pelos c�lculos que desenhar�o a pe�a
	 * no painel em determinada posicao X e Y.
	 * Dire��o 0 = baixo.. dire��o 1 = esquerda. Dire��o 2 = Cima.. dire��o 3 = direita.
	 * ser� visto a dire��o que a pe�a ser� desenhada, e se cabe no painel
	 * @return [X,Y]
	 */
	
	public void calcularPosicaoPeca(JPanel painel, Peca ext1, Peca ext2, Peca first, int direcao, int rotacao){
		int[] posicoes;
		
		// extremidade � esquerda
		if (direcao == 1){
			if ((ext1.posicaoX - 80) < 0){
				posicoes = new int[]{ext1.posicaoX, ext1.posicaoY + 37};
				if (rotacao == 0){
					if (ext1.rotacao == 1 || ext1.rotacao == 3){
						drawPeca(painel, posicoes[0] - 37, posicoes[1], 3);
						return;
					} else {
						drawPeca(painel, posicoes[0], posicoes[1] + 80, 3);
						return;
					}
				} else {
					if (ext1.rotacao == 1 || ext1.rotacao == 3){
						drawPeca(painel, posicoes[0], posicoes[1], rotacao-1);
						return;
					} else {
						drawPeca(painel, posicoes[0], posicoes[1] + 43, rotacao-1);
						return;
					}
				}
			} else {
				posicoes = new int[]{ext1.posicaoX - 80, ext1.posicaoY};
				drawPeca(painel, posicoes[0], posicoes[1], rotacao);
				return;
			}
			
		// extremidade � direita
		} else if (direcao == 3){
			if (ext2.equals(first)){
				posicoes = new int[]{ext2.posicaoX + 37, ext2.posicaoY};
				drawPeca(painel, posicoes[0], posicoes[1], rotacao);
				return;
			} else {
				if ((ext2.posicaoX + 160) > painel.getWidth()){
					posicoes = new int[]{ext2.posicaoX, ext2.posicaoY - 37};
					if (rotacao == 0){
						if (ext2.rotacao == 1 || ext2.rotacao == 3){
							drawPeca(painel, posicoes[0] + 43, posicoes[1], 3);
							return;
						} else {
							drawPeca(painel, posicoes[0], posicoes[1] - 43, 3);
							return;
						}
					} else {
						if (ext2.rotacao == 1 || ext2.rotacao == 3){
							drawPeca(painel, posicoes[0] + 43, posicoes[1] - 43, rotacao-1);
							return;
						} else {
							drawPeca(painel, posicoes[0], posicoes[1] - 43, rotacao-1);
							return;
						}
					}
				} else {
					posicoes = new int[]{ext2.posicaoX + 80, ext2.posicaoY};
					drawPeca(painel, posicoes[0], posicoes[1], rotacao);
					return;
				}
			}
		}
	}

	public int getValor1() {
		return valor1;
	}
	public void setValor1(int valor1) {
		this.valor1 = valor1;
	}
	public int getValor2() {
		return valor2;
	}
	public void setValor2(int valor2) {
		this.valor2 = valor2;
	}
}
