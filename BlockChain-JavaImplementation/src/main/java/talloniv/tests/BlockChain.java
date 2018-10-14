package talloniv.tests;

import java.util.ArrayList;

public class BlockChain {

	static int difficulty = 20;
	static String hashTarget = new String(new char[difficulty]).replace('\0', '0');
	static ArrayList<Block> blockChain = new ArrayList<Block>();
	
	public static void main(String[] args) 
	{
		Block genesisBlock = new Block("GenesisArc", "0");
		System.out.println(genesisBlock.getHash());
		blockChain.add(genesisBlock);
		long start = System.currentTimeMillis();
		genesisBlock.MineBlock(difficulty, hashTarget);
		long end = System.currentTimeMillis();
		System.out.println("Time Elapsed: "+ String.valueOf(end-start));
	}
	
	public static boolean IsChainValid()
	{
		long blockChainSize = blockChain.size();
		Block currentBlock, previousBlock;
		
		for (int i = 1; i < blockChainSize; i++)
		{
			currentBlock = blockChain.get(i);
			previousBlock = blockChain.get(i-1);
			
			if (!currentBlock.getHash().equals(currentBlock.CreateBlockHash()))
			{
				System.out.println("Block hash does not match calculated hash for block.");
				return false;
			}
			
			if (!currentBlock.getPreviousHash().equals(previousBlock.getHash()))
			{
				System.out.println("Previous hash for current block does not match hash for previous block.");
				return false;
			}
			
			if (!currentBlock.getHash().equals(hashTarget))
			{
				System.out.println("Current block has not been mined.");
				return false;
			}
		}
		
		return true;
	}

}
