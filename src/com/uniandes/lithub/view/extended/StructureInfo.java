package com.uniandes.lithub.view.extended;

import java.awt.Component;
import java.util.Enumeration;

import javax.swing.DefaultListModel;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;

import org.jdesktop.swingx.JXCollapsiblePane;

import com.uniandes.lithub.model.Participant;
import com.uniandes.lithub.model.WBStruct;
import java.awt.BorderLayout;

@SuppressWarnings("serial")
public class StructureInfo extends JXCollapsiblePane {

	private DefaultTreeModel dtm;
	
	public StructureInfo() {
		super(Direction.LEFT);
		this.dtm = new DefaultTreeModel(null);
		
		JTree tree = new JTree(dtm);
		add(tree, BorderLayout.WEST);
	}

	public StructureInfo(WBStruct wbs) {
		this();
		disposeTreeModel();
		initialiceTreeModel(wbs);
		
	}

	private void initialiceTreeModel(WBStruct wbs) {
		dtm.setRoot(new TreeNode() {
			
			@Override
			public boolean isLeaf() {
				return false;
			}
			
			@Override
			public TreeNode getParent() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public int getIndex(TreeNode node) {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public int getChildCount() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public TreeNode getChildAt(int childIndex) {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean getAllowsChildren() {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public Enumeration<? extends TreeNode> children() {
				// TODO Auto-generated method stub
				return null;
			}
		});
	}
	
	private void disposeTreeModel() {
		dtm = new DefaultTreeModel(null);
	}
}

@SuppressWarnings("serial")
class TreeRender extends DefaultTreeCellRenderer {
    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf, int row, boolean hasFocus) {
        Component c = super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
        return c;
    }
}