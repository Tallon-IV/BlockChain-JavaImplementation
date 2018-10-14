package talloniv.tests;

import java.nio.charset.StandardCharsets;
import java.util.Random;

import com.google.common.hash.Hashing;

public class Block 
{
	private String hash;
	private String previousHash;
	private Long creationDateTime;
	private String data;
	private Random randomGen = new Random();
	private int nonce = randomGen.nextInt();
	
	public Block(String data, String previousHash)
	{
		this.previousHash = previousHash;
		this.creationDateTime = System.currentTimeMillis();
		this.data = data;
		hash = CreateBlockHash();
	}
	
	public String CreateBlockHash()
	{
		String blockHash = Hashing.sha256()
				.hashString(previousHash
						+ String.valueOf(creationDateTime)
						+ data
						+ String.valueOf(nonce), StandardCharsets.UTF_8).toString();
		
		return blockHash;
	}
	
	public void MineBlock(int difficulty, String miningTarget)
	{
		if (miningTarget == null)
		{
			miningTarget = new String(new char[difficulty]);
			miningTarget = miningTarget.replace('\0', '0');
		}
		
		while (!hash.substring(0, difficulty).equals(miningTarget))
		{
			nonce = randomGen.nextInt();
			hash = CreateBlockHash();
		}
		
		System.out.println(String.format("This block has been successfully mined! Hash: %s", hash));
	}
	
	public String getHash()
	{
		return hash;
	}
	
	public String getPreviousHash()
	{
		return previousHash;
	}
}
