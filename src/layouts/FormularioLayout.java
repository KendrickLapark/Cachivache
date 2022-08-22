package layouts;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class FormularioLayout implements LayoutManager{

	@Override
	public void addLayoutComponent(String name, Component comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeLayoutComponent(Component comp) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Dimension preferredLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Dimension minimumLayoutSize(Container parent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void layoutContainer(Container parent) {
		// TODO Auto-generated method stub
		
		x = 80;
		y = 20;
		
		int contador = 0;
		
		int n = parent.getComponentCount();
				
		for (int i = 0; i < n; i++) {
			
			contador++;
			
			Component component = parent.getComponent(i);
		
			if(contador == 6 || contador == 7 || contador == 8 || contador == 9) {
											
				x+=50;
				
				component.setSize(new Dimension(40,20));
				
				component.setBounds(x, y, 40, 20);
												
				if(contador == 8) {
					component.setSize(new Dimension(60,20));
					x+=20;
				}
				
				if(contador == 9) {
					component.setSize(new Dimension(140,20));
					x=80;
					y+=50;
				}						

			}else if(contador == 6){
				
				
				component.setBounds(x, y, 200, 20);
				
			}else {
				
				component.setBounds(x, y , 200, 20);
				
				x+=150;
				
				if(contador == 5) {
					x-=50;
				}
				
				if(contador %2 ==0 && contador < 10) {
					x=80;
					y+=50;
				}
				
				if(contador>10) {
					if(contador % 2 != 0) {
						x=80;
						y+=50;
					}
				}
			}
		}	
		
	}

	private int x = 0;
	private int y = 0;
	
}
